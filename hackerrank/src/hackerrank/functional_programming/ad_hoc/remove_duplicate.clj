(ns hackerrank.functional-programming.ad-hoc.remove-duplicate)

(let [string (read-line)
      str2 (loop [[a & rst] string
                  cm {}
                  res []]
             (cond (nil? a) res
                   (get cm a) (recur rst cm res) 
                   :else (recur rst (assoc cm a 1) (conj res a))))]
  (println (apply str str2)))

