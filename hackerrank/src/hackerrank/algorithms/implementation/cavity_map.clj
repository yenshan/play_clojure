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
          ]
    (let [adj-cells (map (fn [[a b]] (index a b))
                              [[0 -1] [-1 0] [1 0] [0 1]])
          cav-array (to-array
                      (map #(Character/digit % 10)
                           (apply concat 
                                  (for [_ (range n)]
                                    (vec (read-line))))))
          cavitys (for [i (range (count cav-array))]
                    (if (or (zero? (idx->col i))
                            (= (dec n) (idx->col i))
                            (zero? (idx->row i))
                            (= (dec n) (idx->row i)))
                      (get cav-array i)
                      (let [nn (get cav-array i)
                            adj-nums (->> (map #(+ i %) adj-cells)
                                          (map #(get cav-array %)))]
                        (if (every? #(> nn %) adj-nums)
                          \X
                          (get cav-array i)))))
          result (map #(apply str %) (partition n cavitys))
          ]
      (doseq [e result]
        (println e))
  )))

