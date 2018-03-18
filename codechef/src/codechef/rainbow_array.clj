(ns codechef.rainbow-array
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %)))) 

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [len (Integer/parseInt (read-line))
          dat (str->nums (read-line))
          hlen (quot len 2)
          left (take hlen dat)
          right (take hlen (reverse dat))
          ]
      (println (if (= left right) "yes" "no")))))

