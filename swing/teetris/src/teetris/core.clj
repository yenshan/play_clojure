(ns teetris.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent])
  (:gen-class))

(def BLOCK-SIZE 20)
(def FIELD-WIDTH 10)
(def FIELD-HEIGHT 20)

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

(defn next-type
  [t]
  (let [tbl (keys block-type)
        idx (.indexOf tbl t)]
    (if (= idx (last tbl))
      (nth tbl 0) 
      (nth tbl (inc idx)))))

(defn draw-box
  [g x y]
  (let [px (* x BLOCK-SIZE)
        py (* y BLOCK-SIZE)
        sz (- BLOCK-SIZE 1)]
  (doto g 
    (.setColor java.awt.Color/BLACK)
    (.fillRect px py sz sz))))

(defn cal-h
  [i x op] 
  (-> i (mod 4) (->> (op x))))
(defn cal-h-p [i x] (cal-h i x +))
(defn cal-h-m [i x] (cal-h i x -))

(defn cal-v 
  [i y op]
  (if (< i 4) y (op y 1)))
(defn cal-v-p [i y] (cal-v i y +))
(defn cal-v-m [i y] (cal-v i y -))

(defn draw-block
  [g blk]
  (let [{x :x y :y angl :angle typ :type} blk
        col (block-type typ)]
    (doseq [i (range 0 (count col))]
      (let [e (nth col i)
            opx ({0 cal-h-p, 90 cal-v-p, 180 cal-h-m, 270 cal-v-m} angl)
            opy ({0 cal-v-p, 90 cal-h-m, 180 cal-v-m, 270 cal-h-p} angl)
            px (opx i x)
            py (opy i y)]
        (when (= e 1) (draw-box g px py))))))

(def game-loop 
  (proxy [ActionListener] []
    (actionPerformed [e]
      (if (< (:y @block) FIELD-HEIGHT)
        (swap! block update :y inc)
        (do
          (swap! block assoc :y 0)
          (swap! block update :type next-type)
          )))))


(defn rotate-left [angl] (-> angl (+ 90) (mod 360)))

(def main-panel
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
        (draw-block g @block))
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

(defn -main
  [& args]
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
