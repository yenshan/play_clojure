(ns mult.core
  (:gen-class))

(defn mult' [a b]
  (if (= b 0)
    0
    (+ a (mult' a (dec b)))))

(defn dbl [a] (* a 2))
(defn hlv [a] (/ a 2))

(defn mult
  [a b]
  (cond
    (zero? b) 0
    (even? b) (mult (dbl a) (hlv b))
    :else (+ a (mult a (dec b)))))


(defn -main
  [& args]
  (println (* 12 34))
  (println (mult' 12 34))
  (println (mult 12 34)))
