(ns others.word-count
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn count-words [line]
  (let [w (-> line
              (str/replace #"^\s+" "")
              (str/split #"[\s\t]+"))]
    (if (or (= line "") (= w [""]))
      0
      (count w)))) 

(let [dat (line-seq (io/reader *in*))]
  (println "lines:" (count dat))
  (println "words:" (reduce + (map count-words dat))))
