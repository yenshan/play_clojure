;;
;;https://www.hackerrank.com/challenges/sherlock-and-anagrams/problem?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=dictionaries-hashmaps
;;
(ns hackerrank.interview-preparation-kit.dictionaries-and-hashmaps.sherlock-and-anagrams)

(defn substr [string len]
  (map #(subs string % (+ % len))
       (range (+ (count string) (- len) 1))))

(defn reverse-str [s]
  (apply str (reverse s)))

(defn make-pairs [coll]
  (loop [[x & xs] coll
         res []]
    (if (nil? x)
      res
      (recur xs
             (concat res (for [y xs] [x y]))))))
  
(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [s (read-line)
          pairs (for [i (range 1 (count s))]
                  (make-pairs 
                    (map sort (substr s i))))
          res (apply concat (for [p pairs] 
                              (filter (fn [[a b]] (= a b)) p)))
          ]
      (println (count res)))))
