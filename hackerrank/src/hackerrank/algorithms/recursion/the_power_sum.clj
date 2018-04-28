;;
;; https://www.hackerrank.com/challenges/the-power-sum/problem
;;
(ns hackerrank.algorithms.recursion.the-power-sum)

(defn subset [[a & rst] x]
  (if (nil? a)
    [[]]
    (let [sst (subset rst x)
          sst2 (filter #(>= x (apply + %)) sst)]
      (concat sst2
              (map #(cons a %) sst2)))))

(defn pow-seq [n p]
  (lazy-seq (cons (int (Math/pow n p)) (pow-seq (inc n ) p))))


(let [X (Integer/parseInt (read-line))
      N (Integer/parseInt (read-line))
      pnums (take-while #(<= % X) (pow-seq 1 N))
      psbs (filter #(= X (apply + %)) (subset pnums X))
      ]
  (println (count psbs)))
