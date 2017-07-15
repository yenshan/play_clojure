(ns mult.core
  (:gen-class))

(defn mult' [a b]
  (if (= b 0)
    0
    (+ a (mult' a (dec b)))))

(defn dbl [a] (* a 2))
(defn hlv [a] (/ a 2))

(defn mult
  ([a b] (mult a b 0))
  ([base cnt sum]
   (cond 
     (zero? cnt) sum
     (even? cnt) (recur (dbl base) (hlv cnt) sum)
     :else (recur base (dec cnt) (+ sum base)))))


(defn -main
  [& args]
  (println (* 35 21)
           (mult' 35 21)
           (mult 35 21)))
