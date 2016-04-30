(ns teetris.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent])
  (:gen-class))

(def BLOCK-SIZE 20)
(def FIELD-WIDTH 10)
(def FIELD-HEIGHT 20)

(def field-line (vec (repeat FIELD-WIDTH 0)))
(def field (atom (vec (repeat FIELD-HEIGHT field-line))))
(def block-type {
                 :I [1 1 1 1
                     0 0 0 0]
                 :O [1 1 0 0
                     1 1 0 0]
                 :S [0 1 1 0
                     1 1 0 0]
                 :Z [1 1 0 0
                     0 1 1 0]
                 :L [1 0 0 0
                     1 1 1 0]
                 :J [1 1 1 0
                     1 0 0 0]
                 :T [1 1 1 0
                     0 1 0 0]
                 })

(def block (atom {:x 5 :y 0 
                  :type :I :angle 0}))
;;
;; pure functions for block
;;
(defn next-block-type
  [t]
  (let [tbl (keys block-type)
        nidx (-> tbl
                 (.indexOf t)
                 (inc)
                 (mod (count tbl)))]
    (nth tbl nidx)))

(defn block-dat-size
  [{typ :type}]
  (count (block-type typ)))

(defn block-nth
  [{typ :type} i]
  (let [col (block-type typ)]
    (if (< i (count col))
      (nth col i)
      0)))
;;
;; pure functions for calc block drawing
;;  ex. [0 1 1 0
;;       1 1 0 0]
;;
(defn cal-h [i x op] (-> i (mod 4) (->> (op x))))
(defn cal-h-p [i x] (cal-h i x +))
(defn cal-h-m [i x] (cal-h i x -))
(defn cal-v [i y op] (if (< i 4) y (op y 1)))
(defn cal-v-p [i y] (cal-v i y +))
(defn cal-v-m [i y] (cal-v i y -))

(defn next-x
  [x i angl]
  (let [opx ({0 cal-h-p, 90 cal-v-p, 180 cal-h-m, 270 cal-v-m} angl)]
    (opx i x)))

(defn next-y
  [y i angl]
  (let [opy ({0 cal-v-p, 90 cal-h-m, 180 cal-v-m, 270 cal-h-p} angl)]
    (opy i y)))

(defn blki-to-pos
  "Convert index in block data to position data [x y]"
  [blk i]
  (let [{x :x y :y angl :angle} blk]
    [(next-x x i angl) (next-y y i angl)]))
;;
;; pure function for field
;;
(defn put-box
  [fld x y value] 
  (if (and (> value 0) (>= x 0) (>= y 0))
    (assoc-in fld [y x] value)
    fld))

(defn set-line
  [fld y value]
  (assoc fld y (vec (repeat FIELD-WIDTH value))))

(defn some-in-fld?
  [fld x y]
  (if (or (< x 0) (>= x FIELD-WIDTH))
    true
    (and (>= y 0) (> (get-in fld [y x]) 0))))

(defn blk-hit-fld? 
  [fld blk]
  (letfn [(blk-i-hit-fld? [i]
            (when (> (block-nth blk i) 0) 
              (let [[x y] (blki-to-pos blk i)]
                (some-in-fld? fld x y))))]
    (some blk-i-hit-fld? (range (block-dat-size blk)))))

(defn put-block-on-field
  [fld blk]
  (loop [i 0
         _fld fld]
    (if (>= i (block-dat-size blk))
      _fld
      (let [[x y] (blki-to-pos blk i)
            v (block-nth blk i)]
        (recur (inc i) (put-box _fld x y v))))))

(defn is-filled?
  [dat]
  (= (reduce + dat) (count dat)))

(defn remove-filled-line
  [fld]
  (let [fld2 (vec (filter #(not (is-filled? %)) fld))
        dlt (- (count fld) (count fld2))]
    (loop [_fld fld2
           n dlt]
      (if (<= n 0)
        (vec _fld)
        (recur (cons field-line _fld) (dec n))))))
;; 
;;
(defn draw-box
  [g x y typ]
  (letfn [(draw [g px py sz]
            (doto g 
              (.setColor java.awt.Color/BLACK)
              (.fillRect px py sz sz)))]
    (if (= typ 1)
      (draw g
            (-> x (* BLOCK-SIZE) (+ 1))
            (-> y (* BLOCK-SIZE) (+ 1))
            (- BLOCK-SIZE 2))
      (draw g
            (* x BLOCK-SIZE)
            (* y BLOCK-SIZE)
            BLOCK-SIZE))))

(defn draw-fall-block
  [g blk]
  (let [{x :x y :y angl :angle} blk]
    (doseq [i (range (block-dat-size blk))]
      (let [e (block-nth blk i)
            px (next-x x i angl)
            py (next-y y i angl)]
        (when (= e 1) (draw-box g px py 1))))))

(defn draw-line
  [g y dat]
  (doseq [i (range 0 (count dat))]
    (let [v (nth dat i)]
      (when (> v 0)
        (draw-box g i y v)))))

(defn draw-field
  [g fld]
  (loop [i 0]
    (if (< i (count fld))
      (do 
        (draw-line g i (nth fld i))
        (recur (inc i))))))
;;
;;
(def game-loop 
  (proxy [ActionListener] []
    (actionPerformed [e]
      (let [next-block (update @block :y inc)]
        (if-not (blk-hit-fld? @field next-block)
          (reset! block next-block)
          (do
            (swap! field put-block-on-field @block)
            (swap! field remove-filled-line)
            (swap! block assoc :x 5 :y 0 :angle 0)
            (swap! block update :type next-block-type)
            ))))))

(def main-panel
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (draw-field g @field)
      (draw-fall-block g @block)
      )
    (actionPerformed [e]
      (.repaint this))))

(defn rotate-left [angl] (-> angl (+ 90) (mod 360)))

(defn get-next-block
  [blk tgt opr]
  (let [_blk (update blk tgt opr)]
    (when-not (blk-hit-fld? @field _blk) _blk)))

(def klistn
  (proxy [KeyListener] []
    (keyPressed [e]
      (let [kc (.getKeyCode e)]
        (cond 
          (= kc KeyEvent/VK_LEFT) (when-let [blk (get-next-block @block :x dec)]
                                    (reset! block blk))
          (= kc KeyEvent/VK_RIGHT) (when-let [blk (get-next-block @block :x inc)]
                                     (reset! block blk))
          (= kc KeyEvent/VK_UP) (when-let [blk (get-next-block @block :angle rotate-left)]
                                  (reset! block blk))
          (= kc KeyEvent/VK_DOWN) (when-let [blk (get-next-block @block :y inc)]
                                    (reset! block blk))
          ))) 
    (keyReleased [e])
    (keyTyped [e])))

(defn make-bottom-line [] (swap! field set-line 19 2))

(defn -main
  [& args]
  (make-bottom-line)
  (doto main-panel
    (.setFocusable true)
    (.addKeyListener klistn))
  (doto (JFrame. "Teetris!!!")
    (.add main-panel)
    (.pack)
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize (* FIELD-WIDTH BLOCK-SIZE) (* (+ FIELD-HEIGHT 1) BLOCK-SIZE))
    (.setVisible true))
  (.start (Timer. 200 main-panel))
  (.start (Timer. 500 game-loop)))
