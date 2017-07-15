(ns deep-reverse.core
  (:gen-class))

;;
;; Exercise 2.27
;;
(defn deep-reverse 
  ([coll] (deep-reverse coll (list)))
  ([coll result]
   (if (empty? coll)
     result
     (let [elem (first coll)
           elem' (if (coll? elem) (deep-reverse elem) elem)]
       (recur (rest coll) (cons elem' result))))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
