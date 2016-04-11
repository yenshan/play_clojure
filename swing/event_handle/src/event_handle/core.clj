(ns event-handle.core
  (:import [javax.swing JFrame JPanel]
           [java.awt Dimension]
           [java.awt.event KeyListener])
  (:gen-class))

(def klistn 
  (proxy [KeyListener] []
    (keyPressed [e]
      (let [keyCode (.getKeyCode e)]
        (println keyCode)))
    (keyReleased [e])
    (keyTyped [e])))

(def panel (JPanel.))
(def frame (JFrame. "Heeellooo!!"))

(defn -main
  [& args]
  (doto panel
    (.setFocusable true)
    (.addKeyListener klistn))
  (doto frame
    (.add panel)
    (.pack)
    (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
    (.setSize 400 400)
    (.setVisible true)))
