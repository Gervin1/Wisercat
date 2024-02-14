export interface Criterion {
  id?: number;
  type: string;
  condition: string;
  comparingValue: string;
}

export interface Filter {
  id?: number;
  name: string;
  criteriaList: Criterion[];
  createdAt?: string;
}
