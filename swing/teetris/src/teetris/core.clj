(ns teetris.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent])
  (:gen-class))

(def I-type [1 1 1 1
             0 0 0 0])
(def O-type [1 1 0 0
             1 1 0 0])
(def S-type [0 1 1 0
             1 1 0 0])
(def Z-type [1 1 0 0
             0 1 1 0])
(def L-type [1 0 0 0
             1 1 1 0])
(def J-type [0 0 1 0
             1 1 1 0])
(def T-type [1 1 1 0
             0 1 0 0])

(defn draw-box
  [g x y]
  (doto g 
    (.setColor java.awt.Color/BLACK)
    (.fillRect x y 18 18)))


(defn cal-h
  [i x op] 
  (-> i (mod 4) (* 18) (->> (op x))))
(defn cal-h-p [i x] (cal-h i x +))
(defn cal-h-m [i x] (cal-h i x -))

(defn cal-v 
  [i y op]
  (if (< i 4) y (op y 18)))
(defn cal-v-p [i y] (cal-v i y +))
(defn cal-v-m [i y] (cal-v i y -))

(defn draw-block
  [g x y angl col]
  (doseq [i (range 0 (count col))]
    (let [e (nth col i)
          opx ({0 cal-h-p, 90 cal-v-p, 180 cal-h-m, 270 cal-v-m} angl)
          opy ({0 cal-v-p, 90 cal-h-m, 180 cal-v-m, 270 cal-h-p} angl)
          px (opx i x)
          py (opy i y)]
      (when (= e 1) (draw-box g px py)))))

(def block-table [I-type O-type S-type Z-type L-type J-type T-type])

(def ppx (atom 100))
(def ppy (atom 0))
(def bti (atom 0))
(def angl (atom 0))

(defn main-panel 
  []
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (let [w (proxy-super getWidth)
            h (proxy-super getHeight)
            block-type (nth block-table @bti)]
        (draw-block g @ppx @ppy @angl block-type)
        (swap! ppy + 18)
        (when (>= @ppy h) 
          (do (reset! ppy 0)
              (swap! bti #(mod (inc %) 7))))
        ))
    (actionPerformed [e]
      (.repaint this))))

(def klistn
  (proxy [KeyListener] []
    (keyPressed [e]
      (let [kc (.getKeyCode e)]
        (cond (= kc KeyEvent/VK_LEFT) (swap! ppx - 18)
              (= kc KeyEvent/VK_RIGHT) (swap! ppx + 18)
              (= kc KeyEvent/VK_DOWN) (swap! angl #(mod (+ % 90) 360)))))
    (keyReleased [e])
    (keyTyped [e])))

(defn -main
  [& args]
  (let [panel (main-panel) 
        frame (JFrame. "Test Paint")
        timer (Timer. 500 panel)]
    (doto panel
      (.setFocusable true)
      (.addKeyListener klistn))
    (doto frame
      (.add panel)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize 256 400)
      (.setVisible true))
    (.start timer)))
