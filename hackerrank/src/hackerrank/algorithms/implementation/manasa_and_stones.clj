(ns hackerrank.algorithms.implementation.manasa-and-stones
  (:require [clojure.string :as s]))

(defn last-nums [n a b]
  (letfn [(iter [cnt res]
            (if (zero? cnt)
              (reduce + res)
              (let [ret (for [x [a b]]
                          (iter (dec cnt)
                                (conj res x)))]
                (if (<= cnt 1)
                  ret
                  (apply concat ret)))))
          ]
    (sort (set (iter n [])))))

(let [T (Integer/parseInt (read-line))]
  (doseq [_ (range T)]
    (let [n (Integer/parseInt (read-line))
          a (Integer/parseInt (read-line))
          b (Integer/parseInt (read-line))]
      (loop [[a & rst] (last-nums (dec n) a b)]
        (when a
          (do (print a)
              (when (not (empty? rst))
                (print " "))
              (recur rst))))
      (println "")
      )))

