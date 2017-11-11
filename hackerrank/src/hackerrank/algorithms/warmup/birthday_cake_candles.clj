(ns hackerrank.algorithms.warmup.birthday-cake-candles
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(def _ (read-line))

(->> (str->nums (read-line))
     (reduce (fn [res e]
               (if (get res e)
                 (update res e inc)
                 (assoc res e 1)))
             {})
     vec
     (sort-by first #(compare %2 %1))
     first
     second
     println)



