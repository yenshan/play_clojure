;;
;; https://www.hackerrank.com/challenges/hackerland-radio-transmitters/problem
;;
(ns hackerrank.algorithms.search.hackerland-radio-transmitters
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn far-pos-in-range [a k coll]
  (apply max (cons a (take-while #(< k (- % a)) coll))))

(let [[n k] (str->nums (read-line))
      pos (sort (str->nums (read-line)))
      num-of-radio (loop [[a & rst] pos, res 0]
                     (cond (nil? a) res
                           (empty? rst) (inc res)
                           :else
                           (let [fp (far-pos-in-range a k rst)
                                 ncoll (drop-while #(<= % (+ fp k)) rst)
                                 ]
                             (recur ncoll (inc res)))))
      ]
   (println num-of-radio))


