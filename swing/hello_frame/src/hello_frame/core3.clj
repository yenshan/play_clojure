(ns hello-frame.core3
  (import (javax.swing JFrame))
  (:gen-class))

(def frame (JFrame. "Heeellooo!!"))

(defn -main
  [& args]
  (doto frame
    (.setSize 400 400)
    (.setVisible true)))
