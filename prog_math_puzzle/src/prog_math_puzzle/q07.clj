(ns prog-math-puzzle.q07
  (import (java.util Calendar)
          (java.text SimpleDateFormat)))

(def date-format (SimpleDateFormat. "yyyyMMdd"))

(defn date [dstr]
  (let [cal (Calendar/getInstance)
        d (.parse date-format dstr)]
    (.setTime cal d)
    cal))

(defn to-str [cal]
  (.format date-format (.getTime cal)))

(defn next-day [cal]
  (let [nc (.clone cal)]
    (.add nc Calendar/DATE 1)
    nc))

(defn palendrome-bin? [decstr]
  (let [binstr (-> (Integer/parseInt decstr)
                   (Integer/toBinaryString))
        revbinstr (apply str (reverse binstr))]
    (= binstr revbinstr)))

(defn palendrom-date [startd endd]
  (loop [d startd, res []]
    (if (> (.compareTo d endd) 0)
      res
      (recur (next-day d)
             (if (palendrome-bin? (to-str d)) 
               (conj res (to-str d))
               res)))))

(println (palendrom-date (date "19641010") (date "20200724")))
