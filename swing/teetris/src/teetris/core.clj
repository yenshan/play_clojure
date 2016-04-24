(ns teetris.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event ActionListener KeyListener KeyEvent])
  (:gen-class))

(def i-type [1 1 0 0
             0 1 1 0])

(defn draw-box
  [g x y]
  (doto g 
    (.setColor java.awt.Color/BLACK)
    (.fillRect x y 18 18)))

(defn draw-block
  [g x y col]
  (doseq [i (range 0 (count col))]
    (let [e (nth col i)
          px (-> i (mod 4) (* 18) (+ x))
          py (if (>= i 4) (+ y 18) y)]
      (if (= e 1) (draw-box g px py)))))


(defn main-panel 
  []
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (let [w (proxy-super getWidth)
            h (proxy-super getHeight)]
        (draw-block g 10 10 i-type)
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
