(ns hackerrank.algorithms.greedy.largetst-permutation
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn swap [i j nums]
  (assoc nums i (get nums j) j (get nums i)))

(defn max-i [di nums]
  (loop [i (- (count nums) 2)
         ss (- (count nums) 1)]
    (if (< i di)
      ss
      (let [in (get nums i)
            ssn (get nums ss)]
        (if (> in ssn) 
          (recur (dec i) i)
          (recur (dec i) ss))))))

(defn swap-loop [nums n]
  (loop [si 0
         cnt 0
         res nums]
      (if (or (>= si (- (count nums) 1)) 
              (= cnt n))
        res
        (let [maxi (max-i si res)
              ]
          (if (= maxi si)
            (recur (inc si) cnt res)
            (recur (inc si) (inc cnt) (swap si maxi res)))))))

(defn print-nums [[x & xs]]
  (if (nil? x)
    (println "")
    (do (print x "") (recur xs))))

(let [[l k] (str->nums (read-line))
      nums (vec (str->nums (read-line)))
      ]
  (if (> k l)
    (print-nums (sort #(compare %2 %1) nums))
    (->> (swap-loop nums k)
         print-nums)))
