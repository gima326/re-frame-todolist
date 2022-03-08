(ns re-frame-todolist.views
  (:require
   [re-frame.core :as re-frame]
   [re-frame-todolist.subs :as subs]
   ))

(defn main-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1
      "Hello from " @name]
     ]))

;; step1

(defn list-todo-item [todo]
  [:li (:title todo)])

(defn todo-list []
  [:ul
   (map
    (fn [[idx todo]] ^{:key (:id todo)} [list-todo-item todo])
    @(re-frame/subscribe [::subs/todos]))])
