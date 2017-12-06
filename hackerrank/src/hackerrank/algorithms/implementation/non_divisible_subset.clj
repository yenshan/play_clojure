(ns hackerrank.algorithms.implementation.non-divisible-subset
  (:require [clojure.string :as s]
            [clojure.set :refer [intersection]]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn combinations [coll]
  (-> (for [i coll, j coll, :when (not= i j)]
        (sort [i j]))
      set))

(defn make-subset [coll]
  (apply concat 
    (map (fn [dat] 
           (for [e dat]
             (remove #(= % e) dat)))
         coll)))


(let [[n k] (str->nums (read-line))
      dat (str->nums (read-line))
      ng-comb-map (->> (combinations dat) 
                       (filter (fn [[^long i ^long j]] 
                                 (zero? (mod (+ i j) k))))
                       (reduce (fn [res d]
                                 (assoc res d 1))
                               {}))
      largest-S (loop [subsets [dat], len n]
                  (let [has-ok-coll? (some (fn [d]
                                             (not-any? (fn [e] (ng-comb-map e))
                                                       (combinations d)))
                                           subsets)]
                    (if has-ok-coll?
                      len
                      (recur (make-subset subsets)
                             (dec len)))))
      ]
  (println largest-S))

