import { isNull, isEmpty } from "./common";

export function validate(value: string) {
  return !isNull(value) && !isEmpty(value)
}