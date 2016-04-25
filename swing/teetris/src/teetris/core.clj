(ns teetris.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent])
  (:gen-class))

(def i-type [1 0 0 0
             1 1 1 0])

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
          opx ({0 cal-h-p, 90 cal-v-m, 180 cal-h-m, 270 cal-v-p} angl)
          opy ({0 cal-v-p, 90 cal-h-m, 180 cal-v-m, 270 cal-h-p} angl)
          px (opx i x)
          py (opy i y)]
      (when (= e 1) (draw-box g px py)))))


(defn main-panel 
  []
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (let [w (proxy-super getWidth)
            h (proxy-super getHeight)]
          (draw-block g 50 20 0 i-type)
          (draw-block g 50 100 90 i-type)
          (draw-block g 50 200 180 i-type)
          (draw-block g 50 300 270 i-type)
        ))
    (actionPerformed [e]
      (.repaint this))))

(def klistn
  (proxy [KeyListener] []
    (keyPressed [e]
      (let [kc (.getKeyCode e)]
        (cond (= kc KeyEvent/VK_LEFT) (println "left"))
              (= kc KeyEvent/VK_RIGHT) (println "right")))
    (keyReleased [e])
    (keyTyped [e])))

(defn -main
  [& args]
  (let [panel (main-panel) 
        frame (JFrame. "Test Paint")
        timer (Timer. 10 panel)]
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
