;;
;; https://www.hackerrank.com/challenges/cavity-map/problem
;;
(ns hackerrank.algorithms.implementation.cavity-map
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(let [n (Integer/parseInt (read-line))]
  (letfn [(index [c r] (+ c (* r n)))
          (idx->col [idx] (mod idx n))
          (idx->row [idx] (quot idx n))
          (out-of-scope? [i] 
            (or (= (idx->col i) 0)
                (= (idx->col i) (dec n)) 
                (= (idx->row i) 0)
                (= (idx->row i) (dec n))))
          ]
    (let [adj-cells (map (fn [[a b]] (index a b))
                         [[0 -1] [-1 0] [1 0] [0 1]])
          cav-array (->> (for [_ (range n)]
                           (vec (read-line)))
                         (apply concat) 
                         (map #(Character/digit % 10))
                         to-array)
          cavitys (for [i (range (count cav-array))]
                    (if (out-of-scope? i)
                      (get cav-array i)
                      (let [nn (get cav-array i)
                            adj-nums (map (fn [ri]
                                            (get cav-array (+ i ri)))
                                          adj-cells)]
                        (if (every? #(> nn %) adj-nums)
                          \X
                          nn))))
          result (map #(apply str %) (partition n cavitys))
          ]
      (doseq [e result]
        (println e))
  )))

