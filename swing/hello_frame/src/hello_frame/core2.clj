(ns hello-frame.core2
  (:import [javax.swing JFrame])
  (:gen-class))

(.length "Hello")

(def frame (new JFrame))

(defn -main
  [& args]
  (. frame setSize 400 400)
  (. frame setTitle "Hello,JFrame No.2")
  (. frame setVisible true))
