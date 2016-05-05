(ns teetris.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent])
  (:gen-class))

(def BLOCK-SIZE 20)
(def FIELD-WIDTH 10)
(def FIELD-HEIGHT 20)

(def field-line (vec (repeat FIELD-WIDTH 0)))
(def field (atom (vec (repeat FIELD-HEIGHT field-line))))
(def block (atom {:x 5 :y 0 :type :I :angle 0}))

(def block-type {
                 :I [[0 0][0 1][0 2][0 3]]
                 :O [[0 0][0 1][1 0][1 1]]
                 :S [[0 0][0 1][1 1][1 2]]
                 :Z [[0 0][0 1][-1 1][-1 2]]
                 :L [[0 0][-1 0][-1 1][-1 2]]
                 :J [[0 0][1 0][1 1][1 2]]
                 :T [[0 0][1 0][2 0][1 1]]
                 })
;;
;; pure functions for block
;;
(def rotate-fn {
                0 (fn [[x y]] [x y])
                90 (fn [[x y]] [(* y -1) x])
                180 (fn [[x y]] [(* x -1) (* y -1)])
                270 (fn [[x y]] [y (* x -1)])
                })

(defn movepos
  ([op [dx dy] blk-dat]
   (map (fn [[x y]] [(op x dx) (op y dy)]) blk-dat))
  ([op blk-dat]
   (movepos op (nth blk-dat 1) blk-dat)))

(defn get-block-dat
  [typ angl]
  (let [dat (block-type typ)]
    (if (= typ :O)
      dat
      (->> dat
           (movepos -) 
           (map (rotate-fn angl))))))

(defn next-block-type
  [t]
  (let [tbl (keys block-type)
        nidx (-> tbl
                 (.indexOf t)
                 (inc)
                 (mod (count tbl)))]
    (nth tbl nidx)))
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
  [fld {x :x y :y typ :type angl :angle}]
  (some (fn [[dx dy]] (some-in-fld? fld (+ x dx) (+ y dy)))
        (get-block-dat typ angl)))

(defn put-block-on-field
  [fld {x :x y :y typ :type angl :angle}]
  (let [dat (movepos + [x y] (get-block-dat typ angl))]
    (loop [col dat
           _fld fld]
      (if (empty? col)
        _fld 
        (let [[x y] (first col)]
          (recur (rest col) (put-box _fld x y 1)))))))

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
;; functions for drawing
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
  [g {x :x y :y angl :angle typ :type}]
  (doall (map (fn [[dx dy]] (draw-box g (+ x dx) (+ y dy) 1))
              (get-block-dat typ angl))))

(defn draw-line
  [g y dat]
  (doseq [i (range (count dat))]
    (let [v (nth dat i)]
      (when (> v 0)
        (draw-box g i y v)))))

(defn draw-field
  [g fld]
  (doseq [i (range (count fld))]
    (draw-line g i (nth fld i))))

(def game-loop 
  (proxy [ActionListener] []
    (actionPerformed [e]
      (let [next-block (update @block :y inc)]
        (if-not (blk-hit-fld? @field next-block)
          (reset! block next-block)
          (do
            (swap! field #(-> %
                              (put-block-on-field @block)
                              (remove-filled-line)))
            (swap! block #(-> %
                              (assoc :x 5 :y 0 :angle 0)
                              (update :type next-block-type)))))))))

(def main-panel
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (proxy-super paintComponent g)
      (draw-field g @field)
      (draw-fall-block g @block))
    (actionPerformed [e]
      (.repaint this))))

(defn rotate-left [angl] (-> angl (+ 90) (mod 360)))

(defn change-and-reset!
  [blk tgt opr]
  (let [_blk (update @blk tgt opr)]
    (when-not (blk-hit-fld? @field _blk)
      (reset! blk _blk))))

(def klistn
  (proxy [KeyListener] []
    (keyPressed [e]
      (condp = (.getKeyCode e)
        KeyEvent/VK_LEFT (change-and-reset! block :x dec)
        KeyEvent/VK_RIGHT (change-and-reset! block :x inc)
        KeyEvent/VK_UP (change-and-reset! block :angle rotate-left)
        KeyEvent/VK_DOWN (change-and-reset! block :y inc))) 
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
