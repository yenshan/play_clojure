;;
;;https://www.hackerrank.com/challenges/minimum-swaps-2/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
;;
(ns hackerrank.interview-preparation-kit.arrays.minimum-swaps
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn to-idx-map [coll]
  (reduce (fn [m [i d]] (assoc m i d)) 
          {}
          (map (fn [a b] [a b]) (iterate inc 1) coll)))

(defn swap [m si di]
  (let [x (get m si)
        y (get m di)]
    (-> m
        (assoc si y)
        (assoc di x))))

(let [_ (read-line)
      m (to-idx-map (str->nums (read-line)))
      idxs (sort (keys m))
      swp-cnt (loop [tm m,
                     [i & is :as ix] idxs,
                     cnt 0]
                (cond (nil? is) cnt
                      (= i (get tm i)) (recur tm is cnt)
                      :else (recur (swap tm i (get tm i)) 
                                   ix 
                                   (inc cnt))))
      ]
  (println swp-cnt))


