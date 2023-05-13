(ns vamtyc-admin.component.lib.panel)

(defn panel [header content footer]
  [:div {:class "card"}
   (when header
     [:header {:class "card-header"}
      [:div {:class "card-header-title"}
       header]])
   (when content
      content)
   (when footer
     [:footer {:class "card-footer"}
      footer])])
