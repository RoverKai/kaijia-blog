import { isNull, isEmpty } from "./common";

export function validate(value) {
  return !isNull(value) && !isEmpty(value)
}