(ns hackerrank.algorithms.string.the-love-letter-mistery)


(let [n (Integer/parseInt (read-line))]
  (doseq [_ (range n)]
    (let [string (read-line)
          hlen (/ (count string) 2)
          pre-half (subs string 0 hlen)
          pos-half (take hlen (reverse string))]
      (->> (map #(Math/abs (- (int %1) (int %2)))
                pre-half
                pos-half)
           (reduce +)
           println))))

