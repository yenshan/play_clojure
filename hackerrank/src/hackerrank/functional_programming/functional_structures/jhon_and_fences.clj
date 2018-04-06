(ns hackerrank.functional-programming.functional-structures.jhon-and-fences
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn count-bigger [h fences]
  (count (take-while #(>= (second %) h) fences)))

(defn counts [[i d] fences]
  (let [ls (take i fences)
        rs (drop (inc i) fences)]
    (+ (count-bigger d (reverse ls))
       (count-bigger d rs))))

(let [_ (read-line)
      hs (str->nums (read-line))
      fences (map-indexed vector hs)
      cnt (for [e fences] 
            (* (second e) 
               (inc (counts e fences))))
      ]
  (println (apply max cnt)))

