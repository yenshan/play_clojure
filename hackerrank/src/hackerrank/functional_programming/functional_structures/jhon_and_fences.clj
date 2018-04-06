(ns hackerrank.functional-programming.functional-structures.jhon-and-fences
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn count-bigger [i h f fences]
  (loop [n i
         cnt 0]
    (cond (< n 0) cnt
          (>= n (count fences)) cnt
          (< (get fences n) h) cnt
          :else (recur (f n) (inc cnt)))))

(defn counts [i h fences]
  (+ (count-bigger (inc i) h inc fences)
     (count-bigger (dec i) h dec fences)))

(let [_ (read-line)
      hs (str->nums (read-line))
      fences (to-array hs)
      cnt (for [i (range 0 (count fences))
                :let [h (get fences i)]
                ]
            (* h 
               (inc (counts i h fences))))
      ]
  (println (apply max cnt)))

