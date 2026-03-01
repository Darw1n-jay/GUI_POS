# GUI Layout Guide - Recommended Arrangements

## Overview
This guide provides recommended layouts for arranging GUI components in your POS system for better usability and visual appeal.

## Standard Window Configuration
- **Size**: 950px × 530px
- **Background**: 1.png (all dashboards)
- **Layout Manager**: AbsoluteLayout
- **Font**: Sitka Display, Bold, 12-14pt

---

## 1. ADMIN_DASH - Main Admin Dashboard

### Current Layout Issues:
- Buttons may be scattered
- Inconsistent spacing
- No clear visual grouping

### Recommended Layout:

```
┌────────────────────────────────────────────────────────────┐
│                     [1.png Background]                      │
│                                                              │
│                    ADMIN DASHBOARD                          │
│                                                              │
│    ┌──────────────┐  ┌──────────────┐  ┌──────────────┐   │
│    │  DASHBOARD   │  │   MANAGE     │  │     VIEW     │   │
│    │   SUMMARY    │  │   PRODUCT    │  │  INVENTORY   │   │
│    └──────────────┘  └──────────────┘  └──────────────┘   │
│                                                              │
│    ┌──────────────┐  ┌──────────────┐  ┌──────────────┐   │
│    │  MAKE SALE   │  │   MANAGE     │  │   APPROVE    │   │
│    │              │  │    SALES     │  │   REGISTER   │   │
│    └──────────────┘  └──────────────┘  └──────────────┘   │
│                                                              │
│    ┌──────────────┐  ┌──────────────┐  ┌──────────────┐   │
│    │   MANAGE     │  │    SALES     │  │  LOW STOCK   │   │
│    │    USERS     │  │   REPORT     │  │    ALERT     │   │
│    └──────────────┘  └──────────────┘  └──────────────┘   │
│                                                              │
│    ┌──────────────┐                    ┌──────────────┐   │
│    │   SEARCH     │                    │    LOGOUT    │   │
│    │   PRODUCT    │                    │              │   │
│    └──────────────┘                    └──────────────┘   │
│                                                              │
│              Welcome, Admin! | Date: 2026-03-01            │
└────────────────────────────────────────────────────────────┘
```

### Recommended Coordinates:
```java
// Row 1 (y = 80)
DASHBOARD_SUMMARY:  x=100, y=80,  width=180, height=60
MANAGE_PRODUCT:     x=300, y=80,  width=180, height=60
VIEW_INVENTORY:     x=500, y=80,  width=180, height=60

// Row 2 (y = 160)
MAKE_SALE:          x=100, y=160, width=180, height=60
MANAGE_SALES:       x=300, y=160, width=180, height=60
APPROVE_REGISTER:   x=500, y=160, width=180, height=60

// Row 3 (y = 240)
MANAGE_USER:        x=100, y=240, width=180, height=60
SALES_REPORT:       x=300, y=240, width=180, height=60
LOW_STOCK:          x=500, y=240, width=180, height=60

// Row 4 (y = 320)
SEARCH_PRODUCT:     x=100, y=320, width=180, height=60
LOGOUT:             x=500, y=320, width=180, height=60

// Welcome message
jLabel1:            x=250, y=420, width=450, height=40
```

---

## 2. CASHIER_DASH - Main Cashier Dashboard

### Recommended Layout:

```
┌────────────────────────────────────────────────────────────┐
│                     [1.png Background]                      │
│                                                              │
│                   CASHIER DASHBOARD                         │
│                                                              │
│                                                              │
│              ┌──────────────┐  ┌──────────────┐            │
│              │              │  │              │            │
│              │ CREATE SALE  │  │  MY SALES    │            │
│              │              │  │              │            │
│              └──────────────┘  └──────────────┘            │
│                                                              │
│              ┌──────────────┐  ┌──────────────┐            │
│              │              │  │              │            │
│              │ MY PRODUCTS  │  │   LOGOUT     │            │
│              │              │  │              │            │
│              └──────────────┘  └──────────────┘            │
│                                                              │
│                                                              │
│           Welcome, [Cashier Name]! | Date: 2026-03-01      │
└────────────────────────────────────────────────────────────┘
```

### Recommended Coordinates:
```java
// Title
jLabel1:        x=350, y=50,  width=250, height=40

// Row 1 (y = 150)
CREATE_SALE:    x=250, y=150, width=200, height=80
MY_SALES:       x=500, y=150, width=200, height=80

// Row 2 (y = 260)
MY_PRODUCTS:    x=250, y=260, width=200, height=80
LOGOUT:         x=500, y=260, width=200, height=80

// Welcome message
jLabel2:        x=250, y=420, width=450, height=40
```

---

## 3. MANAGE_PRODUCT_DASH - Product Management

### Recommended Layout:

```
┌────────────────────────────────────────────────────────────┐
│                     [1.png Background]                      │
│  [BACK]                                                     │
│                                                              │
│  ┌─────────────────────────────────┐  Product Details:     │
│  │                                 │                        │
│  │     Product Table               │  Name:    [________]  │
│  │  ID | Name | Price | Stock      │                        │
│  │  ───────────────────────────    │  Price:   [________]  │
│  │  1  | Item | 99.99 | 10         │                        │
│  │  2  | Item | 49.99 | 25         │  Stock:   [________]  │
│  │  ...                            │                        │
│  │                                 │  [  ADD  ]            │
│  │                                 │  [ UPDATE ]           │
│  │                                 │  [ DELETE ]           │
│  └─────────────────────────────────┘                        │
│                                                              │
└────────────────────────────────────────────────────────────┘
```

### Recommended Coordinates:
```java
// Back button
btnBack:        x=30,  y=30,  width=90,  height=35

// Product table
jScrollPane1:   x=30,  y=80,  width=550, height=380

// Form fields (right side)
jLabel1 (Name): x=610, y=100, width=120, height=25
txtName:        x=610, y=130, width=280, height=30

jLabel2 (Price):x=610, y=170, width=120, height=25
txtPrice:       x=610, y=200, width=280, height=30

jLabel3 (Stock):x=610, y=240, width=120, height=25
txtStock:       x=610, y=270, width=280, height=30

// Action buttons
btnAdd:         x=610, y=320, width=85,  height=35
btnUpdate:      x=705, y=320, width=85,  height=35
btnDelete:      x=800, y=320, width=85,  height=35
```

---

## 4. CREATE_SALE_DASH - Sales Creation

### Recommended Layout:

```
┌────────────────────────────────────────────────────────────┐
│                     [1.png Background]                      │
│  [BACK]                                                     │
│                                                              │
│  Available Products:          Shopping Cart:                │
│  ┌──────────────────────┐    ┌──────────────────────┐     │
│  │ ID | Name | Price    │    │ Name | Qty | Price   │     │
│  │ ──────────────────── │    │ ──────────────────── │     │
│  │ 1  | Item | $99.99   │    │ Item | 2   | $199.98 │     │
│  │ 2  | Item | $49.99   │    │ Item | 1   | $49.99  │     │
│  │ ...                  │    │ ...                  │     │
│  └──────────────────────┘    └──────────────────────┘     │
│                                                              │
│  Qty: [___] [ADD TO CART]    [REMOVE]  Total: $249.97     │
│                               [COMPLETE SALE]               │
└────────────────────────────────────────────────────────────┘
```

### Recommended Coordinates:
```java
// Back button
btnBack:        x=30,  y=30,  width=90,  height=35

// Products table (left)
jScrollPane1:   x=30,  y=80,  width=400, height=300

// Cart table (right)
jScrollPane2:   x=450, y=80,  width=450, height=300

// Controls
txtQty:         x=30,  y=400, width=80,  height=30
btnAddToCart:   x=120, y=400, width=140, height=35
btnRemove:      x=450, y=400, width=120, height=35
lblTotal:       x=580, y=400, width=200, height=35
btnComplete:    x=450, y=450, width=200, height=40
```

---

## 5. General Layout Principles

### Spacing Guidelines:
```
Margins:
- Top:    30-50px
- Bottom: 30-50px
- Left:   30px
- Right:  30px

Between Components:
- Buttons (horizontal): 10-20px
- Buttons (vertical):   20-30px
- Form fields:          10-15px
- Sections:             30-40px
```

### Button Sizing:
```
Small buttons:   80-100px  × 30-35px  (Back, Add, Delete)
Medium buttons:  140-180px × 40-50px  (Standard actions)
Large buttons:   200-250px × 60-80px  (Main dashboard)
```

### Color Scheme:
```java
// Buttons
background: new Color(0, 0, 0)      // Black
foreground: new Color(255, 255, 255) // White

// Labels
foreground: new Color(255, 255, 255) // White (on dark background)

// Tables
background: new Color(255, 255, 255) // White
gridColor:  new Color(200, 200, 200) // Light gray
```

### Font Settings:
```java
// Buttons
new Font("Sitka Display", Font.BOLD, 14)

// Labels
new Font("Sitka Display", Font.BOLD, 14)

// Title labels
new Font("Sitka Display", Font.BOLD, 18)

// Table
new Font("Arial", Font.PLAIN, 12)
```

---

## 6. Responsive Design Tips

### For Different Screen Sizes:
1. Use percentages for positioning (when possible)
2. Set minimum window size
3. Consider using layout managers (GridBagLayout, BorderLayout)
4. Test on different resolutions

### Accessibility:
1. Ensure sufficient color contrast
2. Use readable font sizes (minimum 12pt)
3. Provide keyboard shortcuts
4. Add tooltips for clarity
5. Use clear, descriptive labels

---

## 7. Implementation Steps

### To Apply These Layouts in NetBeans:

1. **Open Form in Design View**
   ```
   - Open .java file
   - Click "Design" tab
   - View component hierarchy in Navigator
   ```

2. **Adjust Component Positions**
   ```
   - Select component
   - In Properties window, find "constraints"
   - Set x, y, width, height values
   - Or drag components in design view
   ```

3. **Align Multiple Components**
   ```
   - Select multiple components (Ctrl+Click)
   - Right-click → Align → Choose alignment
   - Use alignment guides (red lines)
   ```

4. **Set Consistent Spacing**
   ```
   - Select components
   - Right-click → Set Same Size
   - Use grid for even spacing
   ```

5. **Test Layout**
   ```
   - Click "Source" tab to see generated code
   - Run application to test
   - Adjust as needed
   ```

---

## 8. Quick Reference - Common Positions

### Standard Window (950×530):
```
Top-left corner:     x=30,  y=30
Top-center:          x=400, y=30
Top-right:           x=820, y=30

Middle-left:         x=30,  y=250
Center:              x=400, y=250
Middle-right:        x=820, y=250

Bottom-left:         x=30,  y=470
Bottom-center:       x=400, y=470
Bottom-right:        x=820, y=470
```

### Button Grid (3×3):
```
Row 1: y=80,  spacing: x=100, 300, 500
Row 2: y=160, spacing: x=100, 300, 500
Row 3: y=240, spacing: x=100, 300, 500
```

### Form Layout (Label + Field):
```
Label:  x=610, y=100, width=120, height=25
Field:  x=610, y=130, width=280, height=30
Gap:    40px between label and next label
```

---

## Summary

✅ **Applied Changes:**
- All dashboards now use 1.png background
- Consistent window sizing (950×530)
- Professional appearance

📐 **Recommended Improvements:**
- Implement grid-based button layouts
- Consistent spacing between components
- Better visual hierarchy
- Improved form alignment

🎨 **Visual Consistency:**
- Uniform backgrounds
- Consistent button sizes
- Standard color scheme
- Professional fonts

---

**Status**: ✅ Layouts documented
**Background**: 1.png (all dashboards)
**Window Size**: 950×530px
**Last Updated**: 2026-03-01
