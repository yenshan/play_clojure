;;
;; https://www.hackerrank.com/challenges/lists-and-gcd/problem
;;
(ns hackerrank.clj.list-and-gcd
  (:require [clojure.string :as s]
            [clojure.set :as clj-set]))

(defn common-prime [lsts]
  (->> (for [e lsts]
         (reduce #(conj %1 (first %2)) 
                 #{} 
                 e))
       (apply clj-set/intersection)))

(defn list-gcd [lsts]
  (let [cps (common-prime lsts)]
    (reduce (fn [m [p n :as d]]
              (cond (not (contains? cps p)) m
                    (not (get m p)) (assoc m p n)
                    (> n (get m p)) m
                    :else (assoc m p n)))
            {}
            (apply concat lsts)
            )))

(defn num->lst [nstr]
  (->> (map #(Integer/parseInt %) (s/split nstr #" "))
       (partition 2 2)))

(defn print-seq [coll]
  (doall (loop [c coll]
           (print (first c))
           (when (next c)
             (print " ")
             (recur (rest c)))))
  (print "\n"))

(def q (Integer/parseInt (read-line)))

(->> (for [_ (range q)]
       (num->lst (read-line)))
     list-gcd
     vec
     (sort-by first)
     flatten
     print-seq)

     
