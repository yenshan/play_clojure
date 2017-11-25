(ns hackerrank.algorithms.implementation.climbing-the-leaderboard
  (:require [clojure.string :as s]))

(defn str->nums [str]
  (->> (s/split str #" ")
       (map #(Integer/parseInt %))))


(defn create-rank-list [coll]
  (sort #(compare (:rank %1) (:rank %2))
        (vec
          (loop [[sc & rst] coll, rank 1, rank-list #{}]
            (if (empty? rst)
              (conj rank-list {:rank rank :score sc})
              (recur rst
                     (if (> sc (first rst)) (inc rank) rank)
                     (conj rank-list {:rank rank :score sc})))))))

(defn check-rank [n coll]
  (let [rank (:rank (some (fn [d]
                            (when (<= (:score d) n) d))
                          coll))]
    (if rank
      rank
      (inc (:rank (last coll))))))

(defn rank-history [your-scores rank-scores]
  (let [rklst (create-rank-list rank-scores)]
    (loop [[a & rst] your-scores, res []]
      (if (nil? a)
        res
        (recur rst
               (conj res (check-rank a rklst)))))))

(let [_ (read-line)
      rank-scores (str->nums (read-line))
      _ (read-line)
      alice-scores (str->nums (read-line))]
  (doseq [r (rank-history alice-scores rank-scores)]
    (println r)))

