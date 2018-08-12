;;
;;https://www.hackerrank.com/challenges/ctci-ransom-note/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
;;
(ns hackerrank.interview-preparation-kit.dictionaries-and-hashmaps.ransom-note
  (:require [clojure.string :as s]))

(defn read-words []
  (-> (read-line)
      (s/split #" ")))

(defn -update [m d f]
  (if-let [v (get m d)]
    (assoc m d (f v))
    (assoc m d 1)))

(defn -main []
  (let [_ (read-line)
        mag-word-map (reduce (fn [m w] (-update m w inc))
                             {}
                             (read-words))
        ran-words (read-words)
        res (loop [[x & xs] ran-words
                   wm mag-word-map]
              (if (nil? x)
                "Yes"
                (if-let [w (get wm x)]
                  (if (zero? w)
                    "No"
                    (recur xs (-update wm x dec)))
                  "No")))
        ]
    (println res)))

(-main)

