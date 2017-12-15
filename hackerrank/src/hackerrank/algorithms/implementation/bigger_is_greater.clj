(ns hackerrank.algorithms.implementation.bigger-is-greater
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))

(defn drop-nth-char [n string]
  (->> (map-indexed vector string)
       (filter (fn [[i _]] (not= i n)))
       (map second)
       (apply str)
       ))

(defn all-str-pattern [string]
  (letfn [(iter [st res]
            (if (empty? st)
              res
              (let [rt (for [i (range (count st))]
                         (iter (drop-nth-char i st)
                               (str res (nth st i))))
                    ]
                (if (= 1 (count rt))
                  rt
                  (apply concat rt)))))]
    (iter string "")))

(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [string (read-line)
          answer (->> (all-str-pattern string)
                      (filter #(< 0 (compare % string)))
                      sort
                      first)]
      (if (nil? answer)
        (println "no answer")
        (println answer)))))


  

