(ns lambda-cons.core
  (:gen-class))

(defn mycons [x y]
  (fn [m] (m x y)))

(defn car [z]
  (z (fn [a b] a)))

(defn cdr [z]
  (z (fn [a b] b)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
