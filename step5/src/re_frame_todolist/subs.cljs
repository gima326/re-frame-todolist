(ns re-frame-todolist.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)
   ))

;; step1

(re-frame/reg-sub
 ::todos
 (fn [db]
   (:todos db)))
