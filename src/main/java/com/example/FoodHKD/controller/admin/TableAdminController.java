package com.example.FoodHKD.controller.admin;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.FoodHKD.model.TableDetail;
import com.example.FoodHKD.model.TableEntity;
import com.example.FoodHKD.service.TableService;

@Controller
@RequestMapping("/admin/tables")
public class TableAdminController {

    @Autowired
    private TableService tableService;

   

    @GetMapping
    public String viewTables(Model model) {
        List<TableEntity> availableTables = tableService.getAllTables();
        model.addAttribute("tables", availableTables);
        return "tablesAdmin";
    }

    @GetMapping("/{tableId}")
    public String viewTableDetails(@PathVariable Integer tableId, Model model) {
        Optional<TableEntity> tableOptional = tableService.getTableById(tableId);
        if (tableOptional.isPresent()) {
            TableEntity table = tableOptional.get();
            List<TableDetail> tableDetails = tableService.getTableDetailsByTable(table);
            BigDecimal totalPrice = tableDetails.stream()
                    .map(TableDetail::getTotalPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            model.addAttribute("table", table);
            model.addAttribute("tableDetails", tableDetails);
            model.addAttribute("totalPrice", totalPrice);
            return "table_detailAdmin";
        } else {
            return "redirect:/admin/tables";
        }
    }
}