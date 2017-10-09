(ns hackerrank.clj.string-o-permute)

(doseq [n (range (Integer/parseInt (read-line)))]
  (let [string (read-line)]
    (when (even? (count string))
      (->> string
           (partition 2 2)
           (reduce (fn [res [a b]] (conj res b a)) [])
           (apply str)
           println))))

