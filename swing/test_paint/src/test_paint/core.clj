(ns test-paint.core
  (:import [javax.swing JFrame JPanel Timer]
           [java.awt.event KeyListener ActionListener])
  (:gen-class))

(def py (atom 0))

(defn next-y
  [y h]
  (if (<= y (- h 10)) (inc y) y))

(def panel 
  (proxy [JPanel ActionListener] []
    (paintComponent [g]
      (let [w (proxy-super getWidth)
            h (proxy-super getHeight)
            x (/ w 2)
            y (next-y @py h)]
        (doto g 
          (.setColor java.awt.Color/BLACK)
          (.fillOval x y 10 10))
        (reset! py y)))
    (actionPerformed [e]
      (.repaint this))))

(defn -main
  [& args]
  (let [frame (JFrame. "Test Paint")
        timer (Timer. 10 panel)]
    (doto frame
      (.add panel)
      (.pack)
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setSize 400 400)
      (.setVisible true))
    (.start timer)))

