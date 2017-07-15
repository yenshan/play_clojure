(ns list.core
  (:gen-class))

(defn list-ref [items n]
  (if (= n 0)
    (first items)
    (list-ref (rest items) (- n 1))))

(defn length [items]
  (if (empty? items)
    0
    (+ 1 (length (rest items)))))

(defn length'
  ([items] (length' items 0))
  ([items count]
    (if (empty? items) count
      (length' (rest items) (+ count 1)))))

(defn append [list1 list2]
  (if (empty? list1)
    list2
    (cons (first list1) (append (rest list1) list2))))

;;
;; Exerciese 2.17
;;
(defn last-pair [coll]
  (if (nil? (next coll))
    (first coll)
    (last-pair (rest coll))))

;;
;; Exercise 2.18
;;
(defn my-reverse 
  ([coll] (my-reverse coll (list)))
  ([coll result]
   (if (empty? coll)
     result
    (recur (rest coll) (cons (first coll) result)))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
