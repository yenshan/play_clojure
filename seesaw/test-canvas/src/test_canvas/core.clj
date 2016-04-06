(ns test-canvas.core
  (:use seesaw.core
        seesaw.graphics
        seesaw.dev)
  (:gen-class))

(def style1
  (style :foreground "#ff0000"
         :stroke (stroke :width 2 :cap :round)))

(defn paint1
  [c g]
  (let [width (.getWidth c)
        height (.getHeight c)
        cpx (/ width 2)
        cpy (/ height 2)
        obj (circle cpx cpy (/ width 3))]
    (draw g obj style1)))

(def main-canvas
  (canvas :id :canvas
          :background "#BBBBBB"
          :size [400 :by 400]
          :paint paint1))

(def main-window
  (frame :title "TestCanvas"
         :content main-canvas
         :width 400 :height 400
         :on-close :exit))

(defn -main
  [& args]
  (invoke-later 
    (-> main-window pack! show!)))
