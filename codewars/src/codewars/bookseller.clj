(ns codewars.bookseller
  (:require [clojure.string :as str]))

(defn stock-list [list-of-books list-of-cat]
  (let [bm (->> list-of-books 
                (map #(str/split % #" "))
                (map (fn [[b n]] [(str (first b)) (Integer/parseInt n)]))
                (reduce (fn [m [c n]]
                          (if (m c)
                            (update m c #(+ n %))
                            (assoc m c n)))
                        {}))]
    
      (map (fn [c] 
             (if (bm c)
               [c (bm c)]
               [c 0]))
           list-of-cat)))

(let [ur ["BBAR 150", "CDXE 515", "BKWR 250", "BTSQ 890", "DRTY 600"]
      vr ["A" "B" "C" "D"]]
  (println (stock-list ur vr)))

