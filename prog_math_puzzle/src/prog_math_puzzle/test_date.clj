(ns prog-math-puzzle.test-date
  (import (java.util Calendar)
          (java.text SimpleDateFormat)))

(def date-format (SimpleDateFormat. "yyyyMMdd"))

(defn date [dstr]
  (let [cal (Calendar/getInstance)]
    (.setTime cal (.parse date-format dstr))
    cal))

(defn to-str [cal]
  (.format date-format (.getTime cal)))

(defn next-day [cal]
  (let [nc (.clone cal)]
    (.add nc Calendar/DATE 1)
    nc))

(defn date-seq [cal]
  (lazy-seq (cons cal (date-seq (next-day cal)))))

(println 
  (map to-str (take 10 (date-seq (date "19991225")))))

