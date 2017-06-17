(ns gcd.core
  (:gen-class))

(defn remainder [a b]
  (if (< a b)
    a
    (remainder (- a b) b)))

(defn gcd [a b]
  (if (zero? b)
    a
    (gcd b (remainder a b))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println (gcd 206 40)))
