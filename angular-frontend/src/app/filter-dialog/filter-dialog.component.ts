import { Component, EventEmitter, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from "@angular/forms";
import { FilterService } from "../filter.service";
import { NgForOf, NgIf } from "@angular/common";

@Component({
  selector: 'app-filter-dialog',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgForOf,
    NgIf,
  ],
  templateUrl: './filter-dialog.component.html',
  styleUrl: './filter-dialog.component.css'
})
export class FilterDialogComponent {
  filterForm: FormGroup;
  @Output() dialogClose = new EventEmitter<boolean>();
  days: number[] = [];
  months: number[] = [];
  years: number[] = [];

  constructor(private fb: FormBuilder, private filterService: FilterService) {
    this.initializeDateValues();
    this.filterForm = this.fb.group({
      name: [ '', Validators.required ],
      criteriaList: this.fb.array([])
    });
    this.addCriteria();
  }

  get criteriaList(): FormArray {
    return this.filterForm.get('criteriaList') as FormArray;
  }


  getConditions(type: string | null): string[] {
    type ConditionType = "NUMBER" | "TEXT" | "DATE";
    if (type === null) {
      return [];
    }
    const conditions: { [K in ConditionType]: string[] } = {
      NUMBER: [ 'NUMBER_LESS_THAN', 'NUMBER_LESS_THAN_OR_EQUAL', 'NUMBER_EQUALS', 'NUMBER_GREATER_THAN', 'NUMBER_GREATER_THAN_OR_EQUAL' ],
      TEXT: [ 'TEXT_STARTS_WITH', 'TEXT_ENDS_WITH', 'TEXT_CONTAINS', 'TEXT_NOT_CONTAINS' ],
      DATE: [ 'DATE_FROM', 'DATE_EQUALS', 'DATE_BEFORE' ]
    };
    return conditions[type as keyof typeof conditions];
  }

  addCriteria() {
    const criteriaFormGroup = this.fb.group({
      type: [ 'NUMBER', Validators.required ],
      condition: [ {value: '', disabled: false}, Validators.required ],
      comparingValue: [ '', Validators.required ],
      comparingDay: [ '' ],
      comparingMonth: [ '' ],
      comparingYear: [ '' ]
    });

    criteriaFormGroup.get('type')?.valueChanges.subscribe(type => {
      const conditionControl = criteriaFormGroup.get('condition');
      if (!conditionControl) return;
      conditionControl.enable();
      conditionControl.setValue('');

      if (type) this.updateCriteriaForm(criteriaFormGroup, type);

      const defaultCondition = this.getConditions(type)[0];
      if (defaultCondition) {
        criteriaFormGroup.patchValue({
          condition: defaultCondition
        });
      }
    });
    this.criteriaList.push(criteriaFormGroup);
  }

  updateCriteriaForm(criteriaFormGroup: FormGroup, type: string) {
    if (type === 'DATE') {
      criteriaFormGroup.get('comparingValue')?.disable();
      criteriaFormGroup.get('comparingValue')?.setValue('');
      criteriaFormGroup.get('comparingDay')?.enable();
      criteriaFormGroup.get('comparingMonth')?.enable();
      criteriaFormGroup.get('comparingYear')?.enable();
      criteriaFormGroup.get('comparingDay')?.setValidators(Validators.required);
      criteriaFormGroup.get('comparingMonth')?.setValidators(Validators.required);
      criteriaFormGroup.get('comparingYear')?.setValidators(Validators.required);
    } else {
      [ 'comparingDay', 'comparingMonth', 'comparingYear' ].forEach(field => {
        criteriaFormGroup.get(field)?.disable();
        criteriaFormGroup.get(field)?.setValue('');
        criteriaFormGroup.get(field)?.clearValidators();
      });
      criteriaFormGroup.get('comparingValue')?.enable();
    }
    criteriaFormGroup.updateValueAndValidity();
  }


  removeCriteria(index: number) {
    this.criteriaList.removeAt(index);
  }

  saveFilter() {
    const filterValue = this.filterForm.value;
    const adjustedCriteriaList = filterValue.criteriaList.map((criteria: any) => {
      if (criteria.type === 'DATE') {
        const comparingValue = criteria.comparingDay && criteria.comparingMonth && criteria.comparingYear
          ? `${criteria.comparingDay}-${criteria.comparingMonth}-${criteria.comparingYear}`
          : '';

        return {
          ...criteria,
          comparingValue,
          comparingDay: undefined,
          comparingMonth: undefined,
          comparingYear: undefined,
        };
      } else {
        return criteria;
      }
    });

    const adjustedFilterValue = { ...filterValue, criteriaList: adjustedCriteriaList };

    if (!this.filterForm.valid) {
      return;
    }

    this.filterService.addFilter(adjustedFilterValue).subscribe(result => {
      console.log('Filter Saved:', result);
      this.closeDialog(true);
    });

  }

  closeDialog(saved: boolean = false) {
    this.dialogClose.emit(saved);
  }

  private initializeDateValues() {
    this.days = Array.from({length: 31}, (_, i) => i + 1);

    this.months = Array.from({length: 12}, (_, i) => i + 1);

    const currentYear = new Date().getFullYear();
    this.years = Array.from({length: currentYear - 1899}, (_, i) => 1900 + i);

  }
}
