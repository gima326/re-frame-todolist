(ns re-frame-todolist.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [re-frame-todolist.events :as events]
   [re-frame-todolist.views :as views]
   [re-frame-todolist.config :as config]
   ))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)

    ;; step1
    ;; (rdom/render [views/main-panel] root-el)

    ;; step5
    ;; (rdom/render [views/todo-list] root-el)
    (rdom/render [views/todo-list2] root-el)
    ))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))
