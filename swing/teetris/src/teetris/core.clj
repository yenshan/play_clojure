(ns teetris.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent])
  (:gen-class))

(def BLOCK-SIZE 20)
(def FIELD-WIDTH 10)
(def FIELD-HEIGHT 20)

(def field2 (atom (->> (repeat FIELD-WIDTH 0) (vec) (repeat FIELD-HEIGHT) (vec))))
(def field (atom (-> (* FIELD-WIDTH FIELD-HEIGHT) (repeat 0) (vec))))
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

(defn pos->idx [x y] (-> y (* FIELD-WIDTH) (+ x)))

(defn next-block-type
  [t]
  (let [tbl (keys block-type)
        nidx (-> tbl
                 (.indexOf t)
                 (inc)
                 (mod (count tbl)))]
    (nth tbl nidx)))

;;
;; pure functions for field
;;
(defn has-box?
  ([fld idx]
   (let [v (nth fld idx)]
     (if (= v 0) false v)))
  ([fld x y]
   (if (or (< x 0) (< y 0))
           false
           (has-box? fld (pos->idx x y)))))

(defn set-box
  ([fld x y value] (assoc fld (pos->idx x y) value))
  ([fld x y] (set-box fld x y 1)))


(defn set-line
  ([fld line value]
   (loop [i 0 fld_ fld]
     (if (>= i FIELD-WIDTH)
       fld_
       (recur (inc i) (set-box fld_ i line value)))))
  ([fld line] (set-line fld line)))

;; ----------------------------------------------
(defn set-box2
  [fld x y value] (assoc-in fld [y x] value))

(defn set-line2
  [fld y value]
  (assoc fld y (vec (repeat FIELD-WIDTH value))))

(defn some-in-fld?
  [fld x y]
  (> (get-in fld [y x]) 0))

;;
;; pure functions for block
;;
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

(defn blk-hit-fld? 
  [blk fld]
  (loop [i 0]
    (if (>= i (count blk))
      false
      (let [[x y] (blki-to-pos blk i)]
        (if (some-in-fld? fld x y)
          true
          (recur (inc i)))))))

;;
;; pure functions for field using block
;;
(defn set-block-to-fld
  [fld blk i]
  (let [{x :x y :y angl :angle} blk
        e (block-nth blk i)
        px (next-x x i angl)
        py (next-y y i angl)]
    (if (= e 1) 
      (set-box fld px py)
      fld)))

(defn put-block
  [fld blk]
    (loop [i 0, fld_ fld]
      (if (>= i (block-dat-size blk))
          fld_
          (let [fld2 (set-block-to-fld fld_ blk i)]
            (recur (inc i) fld2)))))
  
(defn blk-e-hit? 
  [fld blk i]
  (let [{x :x y :y angl :angle} blk
        e (block-nth blk i)
        px (next-x x i angl)
        py (next-y y i angl)]
    (if (= e 1)
      (has-box? fld px py)
      false)))


(defn blk-hit?
  [fld blk]
  (some #(blk-e-hit? fld blk %) (range (block-dat-size blk))))
       
;;
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
      (when-let [v (has-box? dat i)]
        (draw-box g i y v))))

(defn draw-field2
  [g fld]
  (loop [i 0]
    (if (< i (count fld))
      (do 
        (draw-line g i (nth fld i))
        (recur (inc i))))))

;(defn draw-field
;  [g fld]
;  (doseq [i (range 0 (count fld))]
;    (let [x (mod i FIELD-WIDTH)
;          y (quot i FIELD-WIDTH)]
;      (when-let [v (has-box? fld i)]
;        (draw-box g x y v)))))

(def game-loop 
  (proxy [ActionListener] []
    (actionPerformed [e]
      (let [next-blk (update @block :y inc)]
        ;(if (< (:y @block) 18)
        (if-not (blk-hit? @field next-blk)
          (swap! block update :y inc)
          (do
      ;      (swap! field put-block @block)
      ;      (swap! block assoc :y 0)
      ;      (swap! block update :type next-block-type)
            ))))))


(defn rotate-left [angl] (-> angl (+ 90) (mod 360)))

(def main-panel
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (draw-field2 g @field2)
      (draw-fall-block g @block)
      )
    (actionPerformed [e]
      (.repaint this))))

(def klistn
  (proxy [KeyListener] []
    (keyPressed [e]
      (let [kc (.getKeyCode e)]
        (cond (= kc KeyEvent/VK_LEFT) (swap! block update :x dec)
              (= kc KeyEvent/VK_RIGHT) (swap! block update :x inc)
              (= kc KeyEvent/VK_DOWN) (swap! block update :angle rotate-left))))
    (keyReleased [e])
    (keyTyped [e])))

(defn make-bottom-line [] (swap! field2 set-line2 18 2))

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
    (.setSize (* FIELD-WIDTH BLOCK-SIZE) (* FIELD-HEIGHT BLOCK-SIZE))
    (.setVisible true))
  (.start (Timer. 200 main-panel))
  (.start (Timer. 500 game-loop)))
