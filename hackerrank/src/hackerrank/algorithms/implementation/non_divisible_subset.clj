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

(defn is-ok? [k]
  (fn [[^long i ^long j]]
    (not= 0 (mod (+ i j) k))))

(defn remove-pair-contain-num [n coll]
  (remove (fn [[a b]]
            (or (= a n) (= b n))) coll))

(let [[n k] (str->nums (read-line))
      dat (str->nums (read-line))
      all-pairs (combinations dat)
      ng-pairs (remove (is-ok? k) all-pairs)
      ng-num-list (->> (apply concat ng-pairs)
                       (reduce (fn [res d]
                                 (if (res d)
                                   (update res d inc)
                                   (assoc res d 1)))
                               {})
                       (sort-by second #(compare %2 %1))
                       (map first))
      nr (loop [[nn & rst :as nglst] ng-num-list, ngp ng-pairs, cnt 0]
           (if (empty? ngp)
             cnt
             (let [next-ngp (remove-pair-contain-num nn ngp)]
               (if (= (count ngp) (count next-ngp))
                 (recur rst next-ngp cnt)
                 (recur rst next-ngp (inc cnt))))))
      ]
  (println (- n nr))
  )
