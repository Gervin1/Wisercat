import { Component, ComponentFactoryResolver, ComponentRef, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { FilterService } from "../filter.service";
import { FilterDialogComponent } from "../filter-dialog/filter-dialog.component";
import { DatePipe, NgForOf, NgIf } from "@angular/common";
import { MatDialog } from "@angular/material/dialog";
import { Filter } from "../models/filter.model";

@Component({
  selector: 'app-filters-list',
  standalone: true,
  imports: [
    FilterDialogComponent,
    NgForOf,
    DatePipe,
    NgIf
  ],
  templateUrl: './filters-list.component.html',
  styleUrl: './filters-list.component.css'
})
export class FiltersListComponent implements OnInit {
  @ViewChild('dialogContainer', {read: ViewContainerRef}) dialogContainer!: ViewContainerRef;
  filters: any[] = [];
  errorMessage: string | null = null;

  constructor(
    private filterService: FilterService,
    private cfr: ComponentFactoryResolver,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.loadFilters();
  }

  loadFilters() {
    this.filterService.getFilters().subscribe((data: Filter[]) => {
      this.filters = data;
    },
      error =>  {
      console.error("There was an error loading the filters:", error);
      this.errorMessage = "There was an error loading the filters: "+ error;
      }
    );
  }

  openFilterModal() {
    const dialogRef = this.dialog.open(FilterDialogComponent, {
      width: '800px',
      panelClass: 'resizable-dialog'
    });

    dialogRef.afterClosed().subscribe(result => {
      this.loadFilters();
    });
  }

  openFilterNonModal() {
    if (this.dialogContainer.length) {
      this.dialogContainer.clear();
    }

    const componentFactory = this.cfr.resolveComponentFactory(FilterDialogComponent);
    const componentRef = this.dialogContainer.createComponent(componentFactory);

    componentRef.instance.dialogClose.subscribe((saved: boolean) => {
      componentRef.destroy();
      if (saved) {
        this.loadFilters();
      }
    });
  }

}
