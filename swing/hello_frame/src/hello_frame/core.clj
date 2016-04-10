(ns hello-frame.core
  (import (javax.swing JFrame))
  (:gen-class))

(def frame (new JFrame))

(defn -main
  [& args]
  (.setSize frame 400 400)
  (.setTitle frame "Hello,JFrame")
  (.setVisible frame true))
