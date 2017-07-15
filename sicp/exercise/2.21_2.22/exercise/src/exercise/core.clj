(ns exercise.core
  (:gen-class))

;;
;; Exercise 2.21
;;
(defn square [x] (* x x))
(defn square-list [items]
  (if (empty? items)
    nil
    (cons (square (first items))
          (square-list (rest items)))))

(defn square-list' [items]
  (map square items))

;;
;; Exercise 2.22
;;
(defn square-list-iter
  ([items] (square-list-iter items (list)))
  ([items answer]
   (if (empty? items) (reverse answer)
    (recur (rest items)
           (cons (square (first items)) answer)))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
